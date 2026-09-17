---
itemId: sw-gvas-02
itemType: Software Item Spec
itemFulfills: BBN-2
---

# Batch Annotation — POST /v1/annotations/batch

The system shall expose `POST /v1/annotations/batch` accepting a multipart/form-data VCF file of up to 10,000 variants and shall return HTTP 202 with `batch_id`, `variant_count`, `status` and `estimated_completion`.

## Item fields

### Inputs

```
POST /v1/annotations/batch
Content-Type: multipart/form-data
Body: VCF file
```

### Outputs

```
Response: 202 Accepted
{
  "batch_id": "bat_e5f6g7h8",
  "variant_count": 312,
  "status": "processing",
  "estimated_completion": "2026-08-24T10:16:00Z"
}
```

### Rationale

**Source:** PRD-Genomic-Variant-Annotation-Service.docx (§ API Changes)
**Parent product requirements (Jama):** GVA-002 (Jama PR — no Tier-3 item exists at time of authoring)
**Trace note:** GAP — GVA-002 not decomposed; traces to product requirement, skipping Tier 3. Component not stated in PRD. Derived (SMART) from verbatim API definition.
**Priority:** P1
**Component:** Annotation Query Engine

### Acceptance criteria

- VCF with ≤ 10,000 variants returns 202 with the documented fields.
- VCF exceeding 10,000 variants is rejected with a clear message.
- Malformed VCF returns 422 with `VCF parsing failed at line [N]: [reason].`

### Verification approach

- Submit VCFs with 1, 100, 500 and 10,000 variants; verify 202.
- Submit a VCF with 10,001 variants; verify rejection.
- Submit a malformed VCF; verify 422 with line-specific error.
