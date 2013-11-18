package ru.regenix.jphp.tokenizer.token.expr;

import ru.regenix.jphp.tokenizer.TokenType;
import ru.regenix.jphp.tokenizer.token.TokenMeta;

public class BraceExprToken extends ExprToken {

    public enum Kind {
        SIMPLE,
        ARRAY,
        BLOCK;

        public static String toOpen(Kind kind){
            switch (kind){
                case ARRAY: return "[";
                case BLOCK: return "{";
                case SIMPLE: return "(";
            }
            return null;
        }

        public static String toClose(Kind kind){
            switch (kind){
                case ARRAY: return "]";
                case BLOCK: return "}";
                case SIMPLE: return ")";
            }
            return null;
        }
    }

    protected Kind kind;
    protected boolean closed;

    public BraceExprToken(TokenMeta meta) {
        super(meta, TokenType.T_J_BRACE);
        switch (meta.getWord().charAt(0)){
            case '{': kind = Kind.BLOCK; break;
            case '[': kind = Kind.ARRAY; break;
            case '(': kind = Kind.SIMPLE; break;
            case '}': kind = Kind.BLOCK; closed = true; break;
            case ']': kind = Kind.ARRAY; closed = true; break;
            case ')': kind = Kind.SIMPLE; closed = true; break;
            default:
                throw new IllegalArgumentException("Invalid " + meta.getWord() + " word for BraceExprToken");
        }
    }

    public Kind getKind() {
        return kind;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isOpened(){
        return !closed;
    }

    public boolean isClosed(Kind kind){
        return isClosed() && this.kind == kind;
    }

    public boolean isOpened(Kind kind){
        return isOpened() && this.kind == kind;
    }

    public boolean isBlockOpened(){
        return kind == Kind.BLOCK && isOpened();
    }

    public boolean isBlockClosed(){
        return kind == Kind.BLOCK && isClosed();
    }

    public boolean isArrayOpened(){
        return kind == Kind.ARRAY && isOpened();
    }

    public boolean isArrayClosed(){
        return kind == Kind.ARRAY && isClosed();
    }

    public boolean isSimpleOpened(){
        return kind == Kind.SIMPLE && isOpened();
    }

    public boolean isSimpleClosed(){
        return kind == Kind.SIMPLE && isClosed();
    }

    @Override
    public int getPriority() {
        if (closed)
            return 1001;
        return 1000;
    }
}