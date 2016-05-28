.text
main:
move $fp, $sp
sw $ra, 0($sp)
subu $sp, $sp, 4
li $a0, 3
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
_true: .word 1
_false: .word 0
_newline: .asciiz "\n"
.globl main
