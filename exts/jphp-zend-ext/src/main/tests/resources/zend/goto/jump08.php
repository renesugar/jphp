--TEST--
jump 08: goto into loop (forward)
--FILE--
<?php
goto L1;
while (0) {
    L1: echo "bug\n";
}
?>
--EXPECTF--

Fatal error: 'goto' into loop, switch or finally statement is disallowed in %s on line 2, position %d
