CREATE TRIGGER after_create_person_otp
    AFTER INSERT
    ON Person_OTP FOR EACH ROW
BEGIN
    DELETE FROM Person_OTP WHERE Time_Created < NOW() - INTERVAL 30 MINUTE;
END;

INSERT INTO Qcademy.Person_OTP (email, OTP) VALUES ('mahmoud', 'test');
