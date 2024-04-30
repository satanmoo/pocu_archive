cmake_minimum_required(VERSION 3.10)  # CMake의 최소 요구 버전을 명시
project(MinMax)  # 프로젝트 이름 설정

set(CMAKE_C_STANDARD 99)  # C 언어 표준 설정

# 실행 파일 생성을 위한 소스 파일 추가
add_executable(minmax main.c my_algorithm.c)
