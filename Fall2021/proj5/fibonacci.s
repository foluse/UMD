# Fiyin Oluseye, UID: 117165661, directory ID: moluseye 
    
    .text
fibonacci:
    #PROLOGUE
    subu $sp, $sp, 8        # expand stack by 8 bytes
    sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes)
    sw   $fp, 4($sp)        # push $fp (4 bytes)
    addu $fp, $sp, 8        # set $fp to saved $ra

    #BODY
    beqz $a0, case1         # goto case1 if n == 0 (return 0)
    bne $a0, 1, fib_rec     # goto fib_rec if n != 1
    move $v0, $a0           # return 1
    j ret

case1:
    move $v0, $a0           # return 0
    j ret

fib_rec:
    subu $sp, $sp, 4        # expand stack (for n - 1)
    sw  $a0, 4($sp)         # save $ra
    sub $a0, $a0, 1         # n - 1
    jal fibonacci           # rec call

    subu $sp, $sp, 4        # expand stack for n-2
    sw $v0, 4($sp)          # store $v0
    lw $a0, -8($fp)         # restore n
    sub $a0, $a0, 2         # n - 2
    jal fibonacci           # rec cal

    lw $a0, -12($fp)
    add $v0, $v0, $a0

ret:
    #EPILOGUE
    move $sp, $fp           # restore $sp
    lw   $ra, ($fp)         # restore saved $ra
    lw   $fp, -4($sp)       # restore saved $fp
    j    $ra                # return to kernel