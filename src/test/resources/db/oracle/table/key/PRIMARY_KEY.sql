BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE TEST.PRIMARY_KEY';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/
CREATE TABLE TEST.PRIMARY_KEY
(
    ID VARCHAR2(10) NOT NULL
)
/
ALTER TABLE TEST.PRIMARY_KEY
    ADD CONSTRAINT PRIMARY_KEY PRIMARY KEY (ID)