# Attendance tracker csv file format
# Author: Adrian Postolache axp3806@rit.edu
# This file specifies the syntax of reading/writing these files (for if the application ever breaks for whatever reason)

# '#' Is a comment line character
# Empty lines are ignored

# Date line format:
# MM/DD,MM/DD,...
# Specifies all the dates that will be recorded for this recitation section. Must be at the start of each file

# Student Format:
# Last Name,First Name,Student ID,In-app alphanumeric one-character ID (if left blank the program will generate one)
# X/,Nc,Cc,...
# Each element in this line directly corresponds to each date in the date line, so fewer or equal entries is ok, but
# more is not.
# X/: Student did not attend on this day
# Nc: Student did attend this day, but had no computer. c is the character ID of who they were partnered up with.
# Cc: Student did attend this day, and had a computer. c is the character ID of who they were partnered up with.
# '/' in the character ID slot means the student worked alone that day

# EXAMPLE FILE
1/1,01/02,1/03,12/31
STUDENT,TEST,tst1234,a
X/,Nb,Cb,N/
PARTNER,TEST,prt5678,b
C/,Ca,Na,X/