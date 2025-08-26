package ${packageName}.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

<#if mpService>
import top.continew.starter.data.mp.service.impl.ServiceImpl;
<#else>
import top.continew.starter.extension.crud.service.BaseServiceImpl;
import ${packageName}.model.req.${classNamePrefix}Req;
import ${packageName}.model.resp.${classNamePrefix}DetailResp;
import ${packageName}.model.resp.${classNamePrefix}Resp;
import ${packageName}.model.query.${classNamePrefix}Query;
</#if>
import ${packageName}.mapper.${classNamePrefix}Mapper;
import ${packageName}.model.entity.${classNamePrefix}DO;
import ${packageName}.service.${classNamePrefix}Service;
import lombok.extern.slf4j.Slf4j;

/**
 * ${businessName}业务实现
 *
 * @author ${author}
 * @since ${datetime}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ${className}ServiceImpl extends <#if mpService>ServiceImpl<${classNamePrefix}Mapper, ${classNamePrefix}DO> implements ${classNamePrefix}Service<#else>BaseServiceImpl<${classNamePrefix}Mapper, ${classNamePrefix}DO, ${classNamePrefix}Resp, ${classNamePrefix}DetailResp, ${classNamePrefix}Query, ${classNamePrefix}Req> implements ${classNamePrefix}Service </#if>{
	private final ${classNamePrefix}Mapper ${apiName}Mapper;
}