cmake_minimum_required(VERSION 3.0)
project(my_concat_string)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c)

add_executable(my_concat_string ${SOURCE_FILES})