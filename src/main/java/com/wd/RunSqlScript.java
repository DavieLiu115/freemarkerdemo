package com.wd;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

public class RunSqlScript {

	public static void main(String[] args) throws Exception {
		// 1. 获取数据库连接
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/continew_admin", "root", "adminadmin");

		// 2. 创建ScriptRunner实例
		ScriptRunner runner = new ScriptRunner(connection);

		// 3. 可选：配置ScriptRunner行为
		// runner.setErrorLogWriter(null); // 设置错误日志输出
		// runner.setLogWriter(null); // 设置执行日志输出，设为null可关闭日志打印[citation:2]
		// runner.setSendFullScript(true); // 设置是否一次性执行整个脚本[citation:1]

		// 4. 读取SQL脚本文件
		Reader reader = Resources.getResourceAsReader("UserMenu.sql");

		// 5. 执行脚本
		runner.runScript(reader);

		// 6. 关闭连接
		connection.close();

		System.out.println("SQL脚本执行成功！");
	}
}