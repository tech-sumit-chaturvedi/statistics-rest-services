package com.n26.statistics.controller;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.n26.statistics.base.BaseController;
import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;
import com.n26.statistics.service.ServiceImpl;
import com.n26.statistics.validator.TransactionValidation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * This controller contains Save get and delete Transaction methods.
 * @author Sumit.Chaturvedi
 * @since 2021-19-03 21:23
 */
@RestController
@RequestMapping("/api")
@Api(value = "RequestHandler", description = "This controller contains Save get and delete Transaction methods.")
public class Controller extends BaseController{
	
private static final Logger logger = LoggerFactory.getLogger(Controller.class);	

@Autowired
private ServiceImpl serviceImpl;

/**
  @api {post} /api/transactions
  @apiGroup Email Provider
  @apiDescription Service to save the Transaction details, it will affect only transactionList.
  @apiParamExample {json} Request-Payload:
	{
    "amount": "12.3343",
    "timestamp": "2021-03-20T00:12:51.312Z"
   }
  @apiSuccessExample {json} Success-Response: 
  	HTTP/1.1 200 OK
   {
    "success": true,
    "message": "Saved successfully",
    "status": "CREATED"
   }
  @apiSuccessExample {json} Error-Response: 
  	HTTP/1.1 200 OK
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
  @author Sumit.Chaturvedi
  @since 2021-19-03 21:23
  @param transactionReq it contains information required to create transactionReq.
  @throws Exception when any exception occurs while executing Transaction it will throw an exception.
  @see TransactionService 
 */
@ApiOperation(value = "Save Transactions")
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
@PostMapping(value = "/transactions")
public ResponseEntity save(@RequestBody TransactionReq transactionReq) throws Exception {
	logger.info("save()-------> save Method Invoked");
	TransactionValidation.validateTransaction(transactionReq);
	logger.info("save()-------> transactionReq Data Validated Successfully");
	if (transactionReq.isOlderThanSixtySeconds()) {
		logger.info("save()-------> Transaction timestamp is Older than 60s.");
		return new ResponseEntity(new HashMap<String, Object>() {{
				put(SUCCESS_KEY, false);
				put(STATUS, HttpStatus.NO_CONTENT);
				put(MESSAGE_KEY, "Unable to Save, Transaction TimeStamp is older than Sixty Seconds");
			}}, HttpStatus.OK);
	}else if (transactionReq.isFutureTransaction(transactionReq.getTimestamp())) {
		logger.info("save()-------> Transaction timestamp is in future.");
		return new ResponseEntity(new HashMap<String, Object>() {{
				put(SUCCESS_KEY, false);
				put(STATUS, HttpStatus.UNPROCESSABLE_ENTITY);
				put(MESSAGE_KEY, "Unable to Save, Transaction TimeStamp is in Future.");
			}}, HttpStatus.OK);
	}
	logger.info("save()-------> transactionReq timestamp are not older than Sixty seconds or not in Future.");
	boolean success = serviceImpl.save(transactionReq);
	logger.info("save()-------> Transaction details Successfully Saved, and it will affect only transactionList.");
	logger.info("save()-------> save Method end");
	return new ResponseEntity(new HashMap<String, Object>() {
		{
			put(SUCCESS_KEY, success);
			put(STATUS, HttpStatus.CREATED);
			put(MESSAGE_KEY, "Saved successfully");
		}
	}, HttpStatus.OK);
}


/**
  @api {get} /api/statistics
  @apiVersion 1.0
  @apiDescription This endpoint returns the statistics computed on the transactions within the last 60.
  @apiSuccessExample {json} Success-Response: 
  {
  "sum": 46310.0058,
  "avg": 7718.3343,
  "max": 7718.3343,
  "min": 7718.3343,
  "count": 6
  }			
  @author Sumit.Chaturvedi 
  @since 2021-18-03 23:24
  @see StatisticsService 
  */	
@ApiOperation(value = "Get Statistics last 60 minutes")
@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
@GetMapping(value = "/statistics")
public ResponseEntity get() {
	logger.info("get()-------> get Method Invoked");
	StatisticsResp resp =  serviceImpl.get();
	logger.info("get()-------> get Method end");
	return new ResponseEntity(new HashMap<String, Object>() {{
			put(DATA_KEY, resp==null ? "No Transactions Exist." : resp);
			put(SUCCESS_KEY,resp==null ? false : true);
		}}, HttpStatus.OK);
}


/**
  @api {delete} /transactions
  @apiVersion 1.0
  @apiGroup Transactions
  @apiDescription Service to delete the Transaction List permanently
  @apiSuccessExample {json} Success-Response: 
  	HTTP/1.1 200 OK
	{
	   "success": true,
	   "message":"Successfully deleted",	
	   "status" : "NO_CONTENT" 
	}
  @apiSuccessExample {json} Error-Response: 
  	HTTP/1.1 200 OK
  	{
	   "success": false,
	   "message":"Unable to delete,please contact administrator",
	   "status" : "NOT_FOUND"
	}			
  @author Sumit.Chaturvedi
  @since 2021-20-03 09:23
  @see TransactionService
  */
@ApiOperation(value = "Delete Transactions")
@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
@DeleteMapping(value = "/transactions")
public ResponseEntity delete() throws Exception {
	logger.info("delete() -------> delete Method Invoked");
	boolean success = serviceImpl.delete();
	logger.info("delete() -------> delete Method Invoked");
	return new ResponseEntity(new HashMap<String, Object>() {{
			put(SUCCESS_KEY, success);
			put(MESSAGE_KEY, success ? "Successfully deleted"
					: "Unable to delete,please contact administrator");
			put(STATUS, success ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
		}}, HttpStatus.OK);

}


}

