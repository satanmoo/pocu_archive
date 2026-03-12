cmake_minimum_required(VERSION 3.5)
project(white_space_counter)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.c a.c)

add_executable(white_space_counter ${SOURCE_FILES})