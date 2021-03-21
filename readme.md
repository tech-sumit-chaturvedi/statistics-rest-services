Timestamp - Please Send the  Timestamp in  ISO 8601 Format.

I also configured Swagger-UI
 
URL -  http://localhost:8080/api/transactions
METHOD - POST
Request Payload -
{
 "amount": "44",
 "timestamp":  "2021-03-21T11:02:27.320371100Z[UTC]"
}
 
Response -
If timestamp =   "timestamp":  "2021-03-21T11:02:27.320371100Z[UTC]"
{
    "success": true,
    "message": "Saved successfully",
    "status": "CREATED"
}
If amount and timestamp are null then I am throwing custom exceptions in the form of error.
{
    "data": {
        "errors": {
            "amount": "Amount cannot be null",
            "timestamp": "Timestamp cannot be null"
        }
    },
    "success": false,
    "message": "Unable to Save",
    "status": "BAD_REQUEST"
}
 
If timestamp = "2020-03-21T11:02:27.320371100Z[UTC]"  is Older than Sixty Seconds.
{
    "success": false,
    "message": "Unable to Save, Transaction TimeStamp is older than Sixty Seconds",
    "status": "NO_CONTENT"
}
 
If timestamp =  "2022-03-21T11:02:27.320371100Z[UTC]" is in Future.
{
    "success": false,
    "message": "Unable to Save, Transaction TimeStamp is in Future.",
    "status": "UNPROCESSABLE_ENTITY"
}
URL - http://localhost:8080/api/statistics
METHOD - GET
Response -
{
    "data": {
        "sum": 572,
        "avg": 44,
        "max": 44,
        "min": 44,
        "count": 13
    },
    "success": true
}
If No Transaction exists.
Response -
{
    "data": "No Transactions Exist.",
    "success": false
}
 
 
URL - http://localhost:8080/api/transactions
METHOD - DELETE
Response -
 
{
    "success": true,
    "message": "Successfully deleted",
    "status": "NO_CONTENT"
}
 
 
 
 
 
 

