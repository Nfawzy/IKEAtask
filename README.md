**Test Scenario Approaches**

**Pre - Condition** to add your device configuration 
 go to Main >> java >> resources >> config 
 and add device Name / Version 
- **I implemented two approaches for the test scenario:**
    1. One version where the entire scenario is implemented in a single test method for end-to-end flow.
    2. Another version where the scenario is split into multiple test methods using TestNG priorities and dependencies — 
     making it easier to:
  
  1.Trace each step clearly in reports.
  2.Debug or rerun individual steps if needed.
- 
  3.Maintain the code in a modular way.

- **I also integrated screen recording using FFmpeg:**

   The recording logic is implemented and ready.

   It is currently commented out, but can be easily enabled in the future if screen proof of execution is needed (e.g., for visual validation or demo purposes).