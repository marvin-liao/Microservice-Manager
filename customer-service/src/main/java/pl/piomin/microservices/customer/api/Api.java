package pl.piomin.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.piomin.microservices.customer.intercomm.AccountClient;
import pl.piomin.microservices.customer.model.Account;
import pl.piomin.microservices.customer.model.Customer;
import pl.piomin.microservices.customer.model.CustomerType;

@RestController
@RequestMapping("/customers")
@io.swagger.annotations.Api(value = "CUSTOMER", description = "客户相关接口")
public class Api {
	
	@Autowired
	private AccountClient accountClient;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	private List<Customer> customers;
	
	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
	}


	@ApiOperation(value = "根据客户的pesel获取客户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pesel", value = "pesel", required = true,
					dataType = "string", defaultValue = "12345")
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
	@RequestMapping(value = "/pesel/{pesel}", method = RequestMethod.POST)
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Customer.findByPesel(%s)", pesel));
		return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();	
	}


	@ApiOperation(value = "获取所有客户信息")
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
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return customers;
	}


	@ApiOperation(value = "根据客户id查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true,
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
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Customer findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Account> accounts =  accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
}
