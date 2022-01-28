# Fiyin Oluseye, UID: 117165661, directory ID: moluseye

    .text
isqrt:
    #PROLOGUE
    subu $sp, $sp, 8        # expand stack by 8 bytes
    sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes)
    sw   $fp, 4($sp)        # push $fp (4 bytes)
    addu $fp, $sp, 8        # set $fp to saved $ra

    #BODY
    bge $a0, 2, rec         # if n >= 2 goto rec
    move $v0, $a0           # otherwise return n
    j ret
rec:
    subu $sp, $sp, 4        # expand stack for n
    sw  $a0, 4($sp)         # save $ra

    srl $a0, $a0, 2         # n >> 2
    jal isqrt               # recursive call

    lw $t0, 4($sp)          # restore n, put it in $t0
    sll $v0, $v0, 1         # left shift $t0 (<< 1)

    add $t1, $v0, 1         # small + 1 = large

    mul $t2, $t1, $t1       # $t2 <-- (large * large)
    ble $t2, $t0, cond      # goto cond if (large * large <= n)
    j ret

cond:
    move $v0, $t1           # return large
    j ret

ret:
    #EPILOGUE
    move $sp, $fp           # restore $sp
    lw   $ra, ($fp)         # restore saved $ra
    lw   $fp, -4($sp)       # restore saved $fp
    j    $ra                # return to kernel