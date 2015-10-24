# Heart Rate Calculator

Heart Rate Calculator is android app which calculates your heart rate by calculating amount of red in preview frames 
and calculates avearge.

Usage:
  1. Put tip of your index finger on the camera completely covering the camera.
  2. Don't push too tightly to prevent blood circulation from stopping.
  3. Hold your finger for about 30 seconds to get the more accurate reading.
  
Working:
  1. Gets the amount of red in each preview frame.
  2. Decodes and gets the average amount of red component in image.
  3. Calculates the rolling average.
  4. Takes data in chunks of 10 seconds.
  5. Currently using textureview instead of surfaceview for removing 
  preview of camera from user's eyes.
