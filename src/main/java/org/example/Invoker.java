package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {
    public static void invokeAnnotatedMethods(MyClass object) throws Exception {
        // ИСПРАВЛЕНО: Добавлены квадратные скобки [] для массива методов
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            // Получаем аннотацию
            Repeat repeat = method.getAnnotation(Repeat.class);
            if (repeat == null) {
                continue;
            }

            // Проверяем модификатор доступа
            int modifiers = method.getModifiers();
            if (!Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers)) {
                continue;
            }

            // Разрешаем доступ к защищённым и приватным методам
            method.setAccessible(true);

            // Подготавливаем аргументы
            Object[] arguments = new Object[method.getParameterCount()];
            // ИСПРАВЛЕНО: Добавлены квадратные скобки [] для массива типов
            Class<?>[] parameterTypes = method.getParameterTypes();

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == int.class) {
                    arguments[i] = 10;
                } else if (parameterTypes[i] == String.class) {
                    // ИСПРАВЛЕНО: Добавлено присвоение строки и закрыта скобка
                    arguments[i] = "Hello";
                }
            }

            // ИСПРАВЛЕНО: Используем аннотацию repeat, берём количество повторений
            int times = repeat.times();

            // ИСПРАВЛЕНО: Запускаем цикл и вызываем метод через Reflection
            for (int j = 0; j < times; j++) {
                method.invoke(object, arguments);
            }
        }
    }
}
