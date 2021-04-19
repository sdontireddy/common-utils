package com.ucbos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class StringUtils {

	public static String streamToString(final InputStream inputStream) throws Exception {

		try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			return br.lines().parallel().collect(Collectors.joining("\n"));
		} catch (final IOException e) {
			throw new RuntimeException(e);

		}
	}
}
