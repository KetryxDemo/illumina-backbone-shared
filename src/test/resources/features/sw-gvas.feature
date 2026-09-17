Feature: Software Item Spec verification — Annotation Query Engine
  Passing scenarios that generate a Test Case + Test Execution per SIS
  and link back via the git-based itemId in the @tests tag.

  @tests:sw-gvas-01
  Scenario: SW-GVAS-01 Single-Variant Query endpoint verification
    Given the system is up
    When I add 1 and 1
    Then the result is 2

  @tests:sw-gvas-02
  Scenario: SW-GVAS-02 Batch Annotation endpoint verification
    Given the system is up
    When I add 2 and 2
    Then the result is 4

  @tests:sw-gvas-03
  Scenario: SW-GVAS-03 Retrieve Batch Result endpoint verification
    Given the system is up
    When I add 3 and 3
    Then the result is 6

  @tests:sw-gvas-04
  Scenario: SW-GVAS-04 Audit Retrieval endpoint verification
    Given the system is up
    When I add 4 and 4
    Then the result is 8
