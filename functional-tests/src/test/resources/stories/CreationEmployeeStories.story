Scenario: when valid employee created, recieve status CREATED and response "Entity was send to queue"
Given a valid employee
When an employee transmitted to rest
Then status received CREATED
And body received "Entity was send to queue"
And employee saved in database