---
itemId: sw-gvas-06
itemType: Software Item Spec
itemFulfills: KXITM7MQN0GW0YW8DRSXWJM7899G87A
---

# Reclassification Notification Service — alerts Lab Director on ClinVar reclassification of previously queried variants

The Annotation Query Engine shall implement a reclassification-notification subsystem that runs on every ClinVar (and equivalent source) update ingestion, and shall notify the designated Lab Director when a previously queried variant's classification has changed:

1. **Detection.** On completion of a ClinVar (or equivalent reclassification-relevant source) ingestion, the service scans the audit-retained query log for query_ids whose returned classification for the same variant differs from the newly-ingested classification.
2. **Notification payload.** For each detected change, the service emits a durable notification record to the Lab Director's designated channel (email + in-app inbox) containing: `query_id`, `variant`, `original_classification`, `new_classification`, `source_database`, `version_delta` (from_version → to_version), `detected_at`, and a deep link to the audit-retrieval endpoint for the original query.
3. **Latency objective.** Notifications are dispatched within 30 minutes of the update ingestion completing (P95).
4. **Deduplication.** If the same variant reclassifies multiple times within an ingestion window, one notification per variant is emitted, listing the effective delta from last-notified state to current state.
5. **Delivery guarantee.** Notification emission is idempotent on `(query_id, to_version)` and retries on transient delivery failures with a durable spool.
6. **Audit trail.** Every dispatched notification is recorded in the classification audit trail with the same identifiers and retention as the original query.

## Item fields

### Inputs

- Completed source-database ingestion event (source name, from_version, to_version, ingestion_completed_at).
- Retained audit query log entries (query_id, variant, original_classification, source_versions_at_query, queried_at, requesting user's organization).
- Lab Director channel configuration per organization (email + inbox preferences).

### Outputs

```
Notification record (Lab Director channel — email + in-app inbox)
{
  "notification_id": "notif_a1b2c3",
  "query_id": "qry_a1b2c3d4",
  "variant": "chr17:7578406:C>T",
  "original_classification": "Uncertain_Significance",
  "new_classification": "Pathogenic",
  "source_database": "clinvar",
  "version_delta": {"from": "2026-07-01", "to": "2026-08-01"},
  "detected_at": "2026-08-01T05:12:00Z",
  "audit_link": "https://platform.example.com/v1/annotations/audit/qry_a1b2c3d4"
}
```

Operational outputs: emission-lag metric, notification spool depth, deduplication-window counter for the 30-minute SLA and idempotency observability.

### Rationale

Design specification authored to close the "Software requirements covered by design specifications" traceability gap for the parent product requirement *"Notify Lab Director when database updates reclassify previously queried variants"*. Detection uses the existing audit-retained query log (see Audit & Compliance Module) as the source of what was previously returned, so no additional retention footprint is required. Idempotency on `(query_id, to_version)` prevents duplicate notifications from repeated ingestion attempts.

**Software item type:** Function
**Context:** Safety
**Component:** Annotation Query Engine (with Audit & Compliance Module dependency)
