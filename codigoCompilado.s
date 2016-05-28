.text
main:
move $fp, $sp
sw $ra, 0($sp)
subu $sp, $sp, 4
li $a0, 7
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 5
lw $t0, 4($sp)
addiu $sp, $sp, 4
add $a0, $t0, $a0
lw $a0, x
li $v0, 1
syscall
li $v0, 4
la $a0, _newline
syscall
addiu $sp, $sp, 0
lw $ra, 4($sp)
addiu, $sp, $sp, 8
lw $fp, 0($sp)
jr $ra
.data
_x: .word 0
_true: .word 1
_false: .word 0
_newline: .asciiz "\n"
.globl main
