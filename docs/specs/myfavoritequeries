---
itemId: sw-gvas-01
itemType: Software Item Spec
itemFulfills: BBN-49
---

# Single-Variant Query — POST /v1/annotations/query

The Annotation Query Engine shall expose `POST /v1/annotations/query` accepting a JSON body with chromosome, position, reference, alternate and assembly, and shall return HTTP 200 with `query_id`, `variant`, `annotations` (gnomad, clinvar, functional, cosmic) and `queried_at`.

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

### Outputs

```
Response: 200 OK
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

**Source:** PRD-Genomic-Variant-Annotation-Service.docx (§ API Changes)
**Parent product requirements (Jama):** GVA-001-AC1, GVA-001-AC2, GVA-001-AC4, GVA-004-AC4
**Trace note:** Direct — implements the GVA-001 single-variant query. Derived (SMART) from the verbatim API definition.
**Priority:** P1
**Component:** Annotation Query Engine

### Acceptance criteria

- Valid request returns 200 with all fields shown in the API definition.
- Each annotation section carries its source version.
- Invalid coordinates return 400; service unavailability returns 503 with retry-after seconds (GVA-005).
- P95 latency ≤ 500 ms (GVA-001-AC3).

### Verification approach

- Contract test against the documented request/response schema.
- Latency test: 100 concurrent requests, P95 ≤ 500 ms.
