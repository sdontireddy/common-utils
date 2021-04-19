package com.ucbos.utils;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucbos.utils.JSONUtil;

public class TestJSONUtil {

	@Test
	@Ignore
	public void testJSONupdated() {

		String originalJson = "{\n" + "\"session\":\n" + "    {\n" + "        \"name\":\"JSESSIONID\",\n"
				+ "        \"value\":\"5864FD56A1F84D5B0233E641B5D63B52\"\n" + "    },\n" + "\"loginInfo\":\n"
				+ "    {\n" + "        \"loginCount\":77,\n"
				+ "        \"previousLoginTime\":\"2014-12-02T11:11:58.561+0530\"\n" + "    }\n" + "}";

		JsonNode updatedJson = JSONUtil.updateJSON(originalJson, "$.session.name", "56789055");
		assertThat(updatedJson.toString(), containsString("56789055"));
	}

	@Test
	public void testRandom() {
		int randomNum = ThreadLocalRandom.current().nextInt(3, 7 + 1);
		System.out.println(randomNum);
	}

}
