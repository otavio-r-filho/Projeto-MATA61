.text
fat:
move $fp, $sp
sw $ra, 0($sp)
subu $sp, $sp, 4
lw $a0, 4($fp)
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 0
lw $t0, 4($sp)
addiu $sp, $sp, 4
seq $a0, $t0, $a0
beq $a0, 1, true_branch4
b end_if4
true_branch4:
li $a0, 1
addiu $sp, $sp, 0
lw $ra, 4($sp)
addiu $sp, $sp, 12
lw $fp, 0($sp)
jr $ra
addiu $sp, $sp, 0
end_if4:
lw $a0, 4($fp)
sw $a0, 0($sp)
subu $sp, $sp, 4
sw $fp, 0($sp)
subu $sp, $sp, 4
lw $a0, 4($fp)
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 1
lw $t0, 4($sp)
addiu $sp, $sp, 4
sub $a0, $t0, $a0
sw $a0, 0($sp)
subu $sp, $sp, 4
jal fat
lw $t0, 4($sp)
addiu $sp, $sp, 4
mul $a0, $t0, $a0
addiu $sp, $sp, 0
lw $ra, 4($sp)
addiu $sp, $sp, 12
lw $fp, 0($sp)
jr $ra
addiu $sp, $sp, 0
lw $ra, 4($sp)
addiu, $sp, $sp, 12
lw $fp, 0($sp)
jr $ra
main:
move $fp, $sp
sw $ra, 0($sp)
subu $sp, $sp, 4
li $a0, 0
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 0
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 0
sw $a0, 8($sp)
li $a0, 0
sw $a0, 4($sp)
true_branch26:
lw $a0, 8($sp)
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 6
lw $t0, 4($sp)
addiu $sp, $sp, 4
slt $a0, $t0, $a0
beq $a0, 0, end_while26
lw $a0, 4($sp)
sw $a0, 0($sp)
subu $sp, $sp, 4
sw $fp, 0($sp)
subu $sp, $sp, 4
lw $a0, 16($sp)
sw $a0, 0($sp)
subu $sp, $sp, 4
jal fat
lw $t0, 4($sp)
addiu $sp, $sp, 4
add $a0, $t0, $a0
sw $a0, 4($sp)
lw $a0, 8($sp)
sw $a0, 0($sp)
subu $sp, $sp, 4
li $a0, 1
lw $t0, 4($sp)
addiu $sp, $sp, 4
add $a0, $t0, $a0
sw $a0, 8($sp)
addiu $sp, $sp, 0
b true_branch26
end_while26:
lw $a0, 4($sp)
li $v0, 1
syscall
li $v0, 4
la $a0, _newline
syscall
addiu $sp, $sp, 8
lw $ra, 4($sp)
addiu, $sp, $sp, 8
lw $fp, 0($sp)
jr $ra
.data
_true: .word 1
_false: .word 0
_newline: .asciiz "\n"
.globl fat
.globl main
