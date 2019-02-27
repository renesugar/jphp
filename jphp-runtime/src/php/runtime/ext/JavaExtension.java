package php.runtime.ext;

import php.runtime.env.CompileScope;
import php.runtime.env.Context;
import php.runtime.env.Environment;
import php.runtime.env.handler.EntityFetchHandler;
import php.runtime.ext.java.*;
import php.runtime.ext.support.Extension;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

public class JavaExtension extends Extension {
    @Override
    public String getName() {
        return "Java";
    }

    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }

    @Override
    public void onRegister(CompileScope scope) {
        registerClass(scope, JavaException.class);
    }

    public static class JavaObject extends BaseObject {
        public JavaObject(Environment env, ClassEntity clazz) {
            super(env, clazz);
        }
    }
}
