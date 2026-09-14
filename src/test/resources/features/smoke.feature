@tests:BBN-1
Feature: Canary smoke check for Illumina POV Backbone (Shared)
  So that CI wiring is proven end-to-end
  As a Ketryx build reporter
  I want a trivial passing scenario to land as a Test Execution

  Scenario: Basic arithmetic
    Given the system is up
    When I add 2 and 3
    Then the result is 5
