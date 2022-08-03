package com.study.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.interfaces.BufferedReaderProcessor;
import com.study.java.interfaces.Predicate;
import com.study.java.model.Apple;

@ExtendWith(MockitoExtension.class)
public class Chapter03Test {

	@Test
	void processFileTest() throws Exception {
		String result = processFile();
		assertThat(result).isEqualTo("안녕");
	}

	@Test
	void processFileOneLines() throws Exception {
		String result = this.processFile((BufferedReader br) -> br.readLine());
		assertThat(result).isEqualTo("안녕");
	}

	@Test
	void processFileTwoLines() throws Exception {
		String result = this.processFile((BufferedReader br) -> br.readLine() + br.readLine());
		assertThat(result).isEqualTo("안녕하세요");
	}

	@Test
	void predicate() {
		List<String> list = Arrays.asList("", "", "안녕", "", "", "자바8");

		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = Apple.filter(list, nonEmptyStringPredicate);
		System.out.println(nonEmpty);
	}

	public String processFile() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("C:/data.txt"))) {
			return br.readLine();
		}
	}

	public String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("C:/data.txt"))) {
			return p.process(br);
		}
	}
}
