package req

import (
	"${projectName}/module_admin/entity"
	"${projectName}/module_system/db"
)

// ${className} ${businessName!""} Req对象
// author ${author}
// date ${createTime}
type ${className}Req struct {
	<#list fieldConfigs as field>
	${field.fieldName?cap_first} ${field.fieldType} // ${field.comment!""}
</#list>
}

func Convert2${className}(req *${className}Req) entity.${className} {
	return entity.${className}{
	<#list fieldConfigs as field>
		<#if field.fieldType?starts_with("db.")>
		${field.fieldName?cap_first} : ${field.fieldType}(req.${field.fieldName?cap_first}),
		<#else>
		${field.fieldName?cap_first} : req.${field.fieldName?cap_first},
		</#if>
	</#list>
	}
}

func Convert2${className}Req(user *entity.${className}) ${className}Req {
	return ${className}Req{
	<#list fieldConfigs as field>
		<#if field.fieldType?starts_with("db.")>
		${field.fieldName?cap_first} : ${field.fieldType}(${apiName}.${field.fieldName?cap_first}),
		<#else>
		${field.fieldName?cap_first} : ${apiName}.${field.fieldName?cap_first},
		</#if>
	</#list>
	}

}