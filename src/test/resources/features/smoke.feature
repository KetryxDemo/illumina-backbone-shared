@tests:BBN-1
Feature: Canary smoke check for Illumina POV Backbone (Shared)
  Verifies CI ingestion path end-to-end so BBN-1 lands as a Test Case.

  Scenario: Basic arithmetic
    Given the system is up
    When I add 2 and 3
    Then the result is 5
