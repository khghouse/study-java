package com.study.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.interfaces.BufferedReaderProcessor;

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
