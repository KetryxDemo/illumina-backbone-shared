---
itemId: sw-gvas-04
itemType: Software Item Spec
itemFulfills: BBN-7, BBN-8
---

# Audit Retrieval — GET /v1/annotations/audit/{query_id}

The Audit & Compliance Module shall expose `GET /v1/annotations/audit/{query_id}` returning HTTP 200 with `query_id`, `queried_at`, `user_id`, `database_versions` and the original `result`, within 2 seconds, at no charge to the organization.

## Item fields

### Inputs

```
GET /v1/annotations/audit/{query_id}
```

### Outputs

```
Response: 200 OK
{
  "query_id": "qry_a1b2c3d4",
  "queried_at": "2026-08-24T10:15:00Z",
  "user_id": "usr_xyz",
  "database_versions": {...},
  "result": {...}
}
```

### Rationale

**Source:** PRD-Genomic-Variant-Annotation-Service.docx (§ API Changes)
**Parent product requirements (Jama):** GVA-003-AC2, GVA-003-AC3
**Trace note:** Direct — audit retrieval endpoint named in GVA-003 AC3. Derived (SMART) from verbatim API definition.
**Priority:** P1
**Component:** Audit & Compliance Module

### Acceptance criteria

- Valid `query_id` returns 200 with all documented fields.
- Result equals the originally returned annotation.
- P95 latency ≤ 2 s.
- No metering/credit record is emitted for audit retrieval.

### Verification approach

- Retrieve a 24-hour-old `query_id`; verify identical result.
- Retrieve after a database update; verify original data.
- Latency test on 100 stored IDs.
