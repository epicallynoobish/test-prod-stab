FROM scratch as test
COPY *.* --from=<test>
CMD mvn test 