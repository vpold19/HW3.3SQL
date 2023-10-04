--liquibase formatted sql

--changeset vadimp:1
CREATE INDEX student_name_index ON student (name);

--changeset vadimp:2
CREATE INDEX faculty_ind ON faculty (name, color);