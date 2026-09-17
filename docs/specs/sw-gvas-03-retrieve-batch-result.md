---
itemId: sw-gvas-03
itemType: Software Item Spec
itemFulfills: BBN-4
---

# Retrieve Batch Result — GET /v1/annotations/batch/{batch_id}

The system shall expose `GET /v1/annotations/batch/{batch_id}` returning HTTP 200 with `batch_id`, `status`, `variant_count`, `download_url` and `database_versions` once processing is complete, with the annotated VCF preserving input structure and annotations added to the INFO field.

## Item fields

### Inputs

```
GET /v1/annotations/batch/{batch_id}
```

### Outputs

```
Response: 200 OK (when complete)
{
  "batch_id": "bat_e5f6g7h8",
  "status": "completed",
  "variant_count": 312,
  "download_url": "https://platform.example.com/downloads/bat_e5f6g7h8.vcf",
  "database_versions": {"clinvar": "2026-08-01", "gnomad": "4.1", "cosmic": "v99"}
}
```

### Rationale

**Source:** PRD-Genomic-Variant-Annotation-Service.docx (§ API Changes)
**Parent product requirements (Jama):** GVA-002 (Jama PR — no Tier-3 item exists at time of authoring)
**Trace note:** GAP — GVA-002 not decomposed. Derived (SMART) from verbatim API definition.
**Priority:** P1
**Component:** Annotation Query Engine

### Acceptance criteria

- Completed batch returns 200 with all documented fields.
- Annotated VCF available at `download_url` within 60 s for ≤ 500 variants and within 5 min for ≤ 10,000 variants.
- Output VCF preserves input structure; annotations appear in INFO.

### Verification approach

- Poll a submitted batch; verify fields and SLA per batch size.
- Diff input and output VCF; verify only INFO field additions.
