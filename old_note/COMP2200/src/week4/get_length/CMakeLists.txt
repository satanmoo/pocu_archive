cmake_minimum_required(VERSION 3.0)
project(get_length)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c a.c b.c)

add_executable(get_length ${SOURCE_FILES})