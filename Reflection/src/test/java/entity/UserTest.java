package entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

public class UserTest {

    private User user, userClone;
    private enum randomEnum {
        LOW(1), MIDDLE(2), HIGHT(3);

        int type;

        randomEnum(int i) {
            this.type = i;
        }
    }

    @Before
    public void init() throws IllegalAccessException {
        user = new User();
        Random r = new Random();
        String[] randomString = new String[]{"строка1", "String2", "12345"};

        Field[] fields = User.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            String fieldName = f.getName();
            Class fieldType = f.getType();

            if (fieldType == boolean.class) {
                f.setBoolean(user, true);
            } else if (fieldType == byte.class) {
                f.setByte(user, (byte) r.nextInt(50));
            } else if (fieldType == short.class) {
                f.setShort(user, (short) r.nextInt(50));
            } else if (fieldType == int.class) {
                f.setInt(user, r.nextInt(50));
            } else if (fieldType == long.class) {
                f.setLong(user, r.nextLong());
            } else if (fieldType == double.class) {
                f.setDouble(user, r.nextDouble());
            } else if (fieldType == float.class) {
                f.setFloat(user, r.nextFloat());
            } else if (fieldType == char.class) {
                f.setChar(user, (char) r.nextInt(255));
            } else if (fieldType == String.class) {
                f.set(user, randomString[r.nextInt(randomString.length)]);
            } else if (fieldType == Date.class) {
                f.set(user, new Date(Calendar.getInstance().getTime().getTime()));
            } else if (fieldType == BigInteger.class) {
                f.set(user, new BigInteger(5, r));
            } else if (fieldType == BigDecimal.class) {
                f.set(user, new BigDecimal(r.nextInt() * 10000));
            } else if (fieldType == Enum.class) {
                f.set(user, randomEnum.values()[r.nextInt(3)]);
            } else {
                Assert.fail("Not found type " + fieldType + " for field " + fieldName);
            }
        }
        userClone = new User(user);
    }

    @Test
    public void checkCopyConstructorForUserEntity() throws IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (!field.get(user).equals(field.get(userClone))) {
                Assert.fail("Copy construction of " + User.class.getSimpleName() + " does not copy the field " +
                        field.getName() + " with the type " + field.getType());
            }
        }
    }

}