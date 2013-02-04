# Cache Library

Cache-expiration algorithms supported: 
* LFU: Least Frequently Used 
* LRU: Least Recently Used

## Build Instructions

In maven:
```
mvn clean install
```

## Container Usage
To use in an OC4J container, add ```-Doc4j.jmx.security.proxy.off=true``` to java options.


##License

Released under the [MIT license](http://www.opensource.org/licenses/mit-license.php).
