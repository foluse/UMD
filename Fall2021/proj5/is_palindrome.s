# Fiyin Oluseye, UID: 117165661, directory ID: moluseye

    .text
is_palindrome:
    #PROLOGUE
    subu $sp, $sp, 8        # expand stack by 8 bytes
    sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes)
    sw   $fp, 4($sp)        # push $fp (4 bytes)
    addu $fp, $sp, 8        # set $fp to saved $ra

    #BODY
strlen:
    #PROLOGUE
    subu $sp, $sp, 8        # expand stack by 8 bytes
    sw   $ra, 8($sp)        # push $ra (ret addr, 4 bytes)
    sw   $fp, 4($sp)        # push $fp (4 bytes)
    addu $fp, $sp, 8        # set $fp to saved $ra
 
   subu $sp, $sp, 4         # expand stack for string
   move $t5, $a0            # store arg in $t5
   li   $t0, 0              
   sw   $t0, 4($sp)         
   lb   $t1, 0($t5)
 
loop:
   beqz $t1, endloop        
   add  $t5, $t5, 1         
   add  $t0, $t0, 1         
   lb   $t1, 0($t5)         
   j    loop                
 
endloop:
   move $v0, $t0            
   move $sp, $fp            # restore $sp
   lw   $ra, ($fp)          # restore saved $ra
   lw   $fp, -4($sp)        # restore saved $fp
   jal  rest                # return to palindrome function

rest:
    li $t0, 0               # stores counter in $t0
    li $t4, 1               # truth value (0 or 1)
    lb $t1, 0($a0)          # load character into $t1
    add $t2, $v0, $a0       # the string length
    sub $t2, $t2, $t0       # len - i
    sub $t2, $t2, 1         # (len - i) - 1
    lb $t3, 0($t2)
    div $v0, $v0, 2         # len/2

for_loop:
    bge $t0, $v0, ret       # goto ret if (i >= len/2)
    bne $t1, $t3, base      # return 0 if(string[i] != string[len - i - 1])
    add $a0, $a0, 1         
    sub $t2, $t2, 1
    lb $t1, 0($a0)          # move onto next string
    lb $t3, 0($t2)
    add $t0, $t0, 1         # i++
    j for_loop

base:        
    sub $t4, $t4, 1

ret:   
    move $v0, $t4           # returns value in $t4
    #EPILOGUE
    move $sp, $fp           # restore $sp
    lw   $ra, ($fp)         # restore saved $ra
    lw   $fp, -4($sp)       # restore saved $fp
    j    $ra                # return to kernel



