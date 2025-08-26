package entity

import (
	"${projectName}/module_system/db"
)

// ${className} ${businessName!""}
// author ${author}
// date ${createTime}
type ${className} struct {

<#if fieldConfigs?? >
<#list fieldConfigs as field>
	${field.fieldName?cap_first} ${field.fieldType} `gorm:"<#if field.isPrimary>primary_key;</#if>type:${field.columnType};<#if field.isNotNull>not</#if> null;" json:"${field.fieldName}"`  <#if field.comment?? >// ${field.comment!""}</#if>
</#list>
</#if>
}

// TableName 解决gorm表明映射
func (${className}) TableName() string {
	return "${tableName}"
}
