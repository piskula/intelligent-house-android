## Android APP for [Intelligent House](https://github.com/piskula/intelligent-house) project

This is Android tracking application written in [Kotlin](https://kotlinlang.org/) language, which purpose is to reasonably show LIVE data from your facility, like temperature (in rooms), CO2, last detected motion and so on...


#### Idea

This project has these three parts:
1. Raspberry Pi [scripts](https://github.com/piskula/intelligent-house/tree/master/raspberry_scripts), which periodically send data from your facility to:
2. your Firebase DB, which also has some [trigger functions](https://github.com/piskula/intelligent-house/tree/master/functions)
3. this Android Application
