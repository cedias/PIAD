PIAD
====

Spam Review Detection

##Required
 - java 7 jdk
 - ant
 - python/mysql-connector-python (optional)

##Use

conf:

```
  cd PIAD/config
  gedit database.conf
  ./createDB <db> <user> <pass>
```
  
Run:

  ```
    cd PIAD/SRD
    ant build
    ant FullMain
  ```
  
  
