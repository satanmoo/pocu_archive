cmake_minimum_required(VERSION 3.0)
project(string_toupper_tolower)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c a.c)

add_executable(string_toupper_tolower ${SOURCE_FILES})