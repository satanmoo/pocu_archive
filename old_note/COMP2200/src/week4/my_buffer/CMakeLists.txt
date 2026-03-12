cmake_minimum_required(VERSION 3.0)
project(my_buffer)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c buffered_print.c)

add_executable(my_buffer ${SOURCE_FILES})