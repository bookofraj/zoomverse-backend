package com.codeofraj.zoomverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZoomverseApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZoomverseApplication.class, args);

		System.out.println("""
				888888888888                                            8b           d8                                             \s
				         ,88                                            `8b         d8'                                             \s
				       ,88"                                              `8b       d8'                                              \s
				     ,88"     ,adPPYba,    ,adPPYba,   88,dPYba,,adPYba,  `8b     d8'  ,adPPYba,  8b,dPPYba,  ,adPPYba,   ,adPPYba, \s
				   ,88"      a8"     "8a  a8"     "8a  88P'   "88"    "8a  `8b   d8'  a8P_____88  88P'   "Y8  I8[    ""  a8P_____88 \s
				 ,88"        8b       d8  8b       d8  88      88      88   `8b d8'   8PP""\"""\""  88           `"Y8ba,   8PP""\"""\"" \s
				88"          "8a,   ,a8"  "8a,   ,a8"  88      88      88    `888'    "8b,   ,aa  88          aa    ]8I  "8b,   ,aa \s
				888888888888  `"YbbdP"'    `"YbbdP"'   88      88      88     `8'      `"Ybbd8"'  88          `"YbbdP"'   `"Ybbd8"' \s
				""");
	}

}
