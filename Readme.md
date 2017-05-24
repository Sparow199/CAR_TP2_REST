# TP2 CAR : REST 

This project provides a simple framework [[1]] for the execution of programs
Accessible as REST resources.
The resources are programmed how annotated classes with the JAX-RS API.
See an example with the class car.tp2.HelloWorldResource.
In order to be taken into account by the framework, resources must be
Declarees in class car.tp2.Config, addResources method. The declaration
Done by adding a line of the form:
Resources.add (new MyClassDeResource ())
As many resources as necessary can be declared.
The framework is launched by invoking the Main method of the class
car.tp2.Starter.


# Structure:

```
CAR_TP2_REST
|____src
| |____test
| | |____java
| | | |____test
| | | | |____TestUnitaires.java
| |____main
| | |____resources
| | |____java
| | | |____tp2
| | | | |____Converter.java
| | | | |____HelloWorldResource.java
| | | | |____Config.java
| | | | |____Ressources.java
| | | | |____HtmlMakeTool.java
| | | | |____Constants.java
| | | | |____Starter.java
|____Readme.md
|____pom.xml

```
#### Launch gateway

```
java -jar REST.jar
```

By default set your FTP server to port 2121.

### Use of API
Once launched, the resources are accessible, for example via a
Browser, loading a URL of the form:
```
 http://localhost:8080/rest/tp2/helloworld
 http://localhost:8080/rest/tp2/api/_ressource_
```
#### Wellcome
```
 http://localhost:8080/rest/tp2/api
```
#### Connect to the server and authenticate.
```
 http://localhost:8080/rest/tp2/api/connexion/username/password
```
#### Download the file located on the server.
```
 http://localhost:8080/rest/tp2/api/get/file_path
```

#### View the list of files in the folderpath
```
 http://localhost:8080/rest/tp2/api/list/folder_path
```
#### Upload a file to the server
```
 http://localhost:8080/rest/tp2/api/upload/file_path/file_name/folder_path
```
#### make a folder on the server
```
 http://localhost:8080/rest/tp2/api/mkdir/folder_path/folder_name
```
#### Delete a file on the server
```
 http://localhost:8080/rest/tp2/api/delete/folder_path/file_path
```
#### Rename a file or directory on the server
```
 http://localhost:8080/rest/tp2/api/rename/folder_path/old_file_or_folder_path/new_file_or_folder_path
```

#### Delete a directory on the server
```
$ http://localhost:8080/rest/tp2/api/rmdir/folder_path/folder_name
```
#### Change directory on serverr
```
$ http://localhost:8080/rest/tp2/api/cwd/folder_path
```
#### Go to the root directory on the server
```
$ http://localhost:8080/rest/tp2/api/cdup
```
#### Disconnecting the user from the server
```
$ http://localhost:8080/rest/tp2/api/disconnect
```
[1]: http://aredko.blogspot.fr/2013/01/going-rest-embedding-jetty-with-spring.html

   

