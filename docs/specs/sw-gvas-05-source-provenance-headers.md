---
itemId: sw-gvas-05
itemType: Software Item Spec
itemFulfills: BBN-5
---

# Source Provenance Response Headers — database version and last-update timestamp on annotation responses

The Annotation Query Engine shall attach source-provenance HTTP response headers to every response that carries annotation data — `POST /v1/annotations/query`, `GET /v1/annotations/batch/{batch_id}` and `GET /v1/annotations/audit/{query_id}`:

1. For each source database contributing to the response, emit `X-GVAS-Source-Version: <source>=<version>` and `X-GVAS-Source-Updated: <source>=<ISO-8601 timestamp>`, where `<source>` is one of `gnomad`, `clinvar`, `functional`, `cosmic`. Multiple sources are emitted as a comma-separated list in a single header instance per header name.
2. Headers reflect the source snapshot actually used to resolve that response, not the newest snapshot available at response time — so a re-read of a historical `query_id` returns the versions in force when the query ran.
3. Headers are emitted only for sources that contributed to the response; a source that was not consulted is absent rather than empty.
4. Header values are byte-identical to the corresponding `version` values in the response body, so header and body provenance can never disagree.
5. Provenance headers are omitted on responses that carry no annotation data (validation errors, `202 Accepted` batch submissions).

## Item fields

### Inputs

```
POST /v1/annotations/query
{
  "chromosome": "chr17",
  "position": 7578406,
  "reference": "C",
  "alternate": "T",
  "assembly": "GRCh38"
}
```

Provenance inputs: the per-source snapshot registry (source name, active version, last-ingestion completion timestamp), and the set of sources consulted while resolving the query.

### Outputs

```
Response: 200 OK
X-GVAS-Source-Version: gnomad=4.1, clinvar=2026-08-01, cosmic=v99
X-GVAS-Source-Updated: gnomad=2026-07-14T02:11:00Z, clinvar=2026-08-01T04:30:00Z, cosmic=2026-06-02T01:05:00Z
Content-Type: application/json

{
  "query_id": "qry_a1b2c3d4",
  "variant": "chr17:7578406:C>T",
  "annotations": {
    "gnomad":     {"allele_frequency": 0.00012, "version": "4.1"},
    "clinvar":    {"classification": "Pathogenic", "review_status": "reviewed_by_expert_panel", "version": "2026-08-01"},
    "functional": {"sift": "Deleterious", "polyphen2": "Probably_Damaging", "cadd_score": 35.2},
    "cosmic":     {"occurrence_count": 4821, "version": "v99"}
  },
  "queried_at": "2026-08-24T10:15:00Z"
}
```

### Rationale

Design specification authored to close the "Software requirements covered by design specifications" traceability gap for BBN-5. The requirement is an auditability control: a clinical reviewer must be able to establish, from the response alone, exactly which source snapshots produced an annotation. Pinning the headers to the snapshot used (rather than the current snapshot) is what makes historical queries reproducible.

**Software item type:** Function
**Context:** Regulatory
**Component:** Annotation Query Engine
