package pl.piomin.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import pl.piomin.microservices.account.model.Account;

@RestController
@RequestMapping("/accounts")
@io.swagger.annotations.Api(value = "ACCOUNTS", description = "账户相关接口")
public class Api {

	private List<Account> accounts;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	public Api() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, 1, "111111"));
		accounts.add(new Account(2, 2, "222222"));
		accounts.add(new Account(3, 3, "333333"));
		accounts.add(new Account(4, 4, "444444"));
		accounts.add(new Account(5, 1, "555555"));
		accounts.add(new Account(6, 2, "666666"));
		accounts.add(new Account(7, 2, "777777"));
	}


	@ApiOperation(value = "根据账户的number查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "number", value = "number", required = true,
					dataType = "string", defaultValue = "111111")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
			@ApiResponse(code = 403, message = "服务器拒绝请求"),
			@ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
			@ApiResponse(code = 500, message = "服务器不能完成请求")}
	)
	@ResponseBody
	@RequestMapping(value = "/{number}",method = RequestMethod.POST)
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}


	@ApiOperation(value = "根据账户的customerid查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customer", value = "customer", required = true,
					dataType = "integer", defaultValue = "1")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
			@ApiResponse(code = 403, message = "服务器拒绝请求"),
			@ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
			@ApiResponse(code = 500, message = "服务器不能完成请求")}
	)
	@ResponseBody
	@RequestMapping(value = "/customer/{customer}", method = RequestMethod.POST)
	public List<Account> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
	}


	@ApiOperation(value = "获取所有账户相关信息")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful — 请求已完成"),
			@ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
			@ApiResponse(code = 403, message = "服务器拒绝请求"),
			@ApiResponse(code = 401, message = "未授权客户机访问数据"),
			@ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
			@ApiResponse(code = 500, message = "服务器不能完成请求")}
	)
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Account> findAll() {
		logger.info("Account.findAll()");
		return accounts;
	}
	
}
