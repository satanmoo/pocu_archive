cmake_minimum_required(VERSION 3.0)
project(print_ascii_table)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c a.c)

add_executable(print_ascii_table ${SOURCE_FILES})