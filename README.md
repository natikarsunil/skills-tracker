## Skills Tracker technical test

### Installation steps

Maven:
mvn clan install
mvn spring-boot:run
Application base url : http://localhost:9091/

or 

Docker:
can build and run docker image from Doskerfile and docker-compose.yml file in root directory.


### Documentation

To view the rest endpoint documentation please see
 
"artifacts\generated-docs\skills-tracker-rest-doc.html"


### Postman collection

To import postman collection use below json file:

"artifacts/skill-tracker.postman_collection.json"


### Sample use case
1) Create skill category and skills within this category.

   (see "POST Skill Category Example" in "artifacts\generated-docs\skills-tracker-rest-doc.html")

    This will do the following:
    
    a. Creates a Skill
    
    b. Creates a Skill Category
    
    c. Creates a Skill Category mapping to map a. and b. 

2) Get Skill category map id to assign to an employee

   (see section "GET Skill category mapping Example" in "artifacts\generated-docs\skills-tracker-rest-doc.html")

3) Create an employee and assign a skill

   (see section "POST Employee Example" in "artifacts\generated-docs\skills-tracker-rest-doc.html")

   set id from step 2 into skillCategoryMapId 
   
   this will create an employee and will assign skills created in above steps
   
   response will display employee details with skills assigned   
