# Fiyin Oluseye, UID: 117165661, directory ID: moluseye

    .text
reverse_prefix_sum:
    #PROLOGUE
    subu $sp, $sp, 8        # expand stack by 8 bytes
    sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes)
    sw   $fp, 4($sp)        # push $fp (4 bytes)
    addu $fp, $sp, 8        # set $fp to saved $ra
    
    #BODY
    lw $t0, 0($a0)           
    bne $t0, -1, rec        # goto rec if *arr != -1
    li $t1, 0               # $t1 <-- 0
    move $v0, $t1           # return 0
    j ret

rec:
    subu $sp, $sp, 4        # expand stack for (arr+1)
    sw $a0, 4($sp)          # store n
    add $a0, $a0, 4         # increment *arr by 4

    jal reverse_prefix_sum  # recursive call
    lw $a0, -8($fp)         # restore n
    lw $t2, 0($a0)          # *arr
    addu $t3, $v0, $t2      # r = reverse_prefix_sum(arr+1) + (uint32_t)*arr

    sw $t3, 0($a0)          # r <-- *arr
    move $v0, $t3           # return r

ret:
    #EPILOGUE
    move $sp, $fp           # restore $sp
    lw   $ra, ($fp)         # restore saved $ra
    lw   $fp, -4($sp)       # restore saved $fp
    j    $ra                # return to kernel