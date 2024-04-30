cmake_minimum_required(VERSION 3.0)
project(my_strtok)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c a.c)

add_executable(my_strtok ${SOURCE_FILES})