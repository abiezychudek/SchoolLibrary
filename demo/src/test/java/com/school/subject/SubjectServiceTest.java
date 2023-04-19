package com.school.Service;


import com.school.book.BookService;
import com.school.subject.Subject;
import com.school.subject.SubjectRepository;
import com.school.subject.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private SubjectRepository subjectRepository;
    private BookService bookService;
    private SubjectService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SubjectService(subjectRepository);
    }

    @Test
    void shouldAddMember() {
        //given
        Subject member = new Subject("Jan", "Brzechwa", 22);
        //when
        underTest.addMember(member);
        //then
        ArgumentCaptor<Subject> memberArgumentCaptor = ArgumentCaptor.forClass(Subject.class);
        verify(subjectRepository).save(memberArgumentCaptor.capture());
        Subject capture = memberArgumentCaptor.getValue();
        assertThat(member).isEqualTo(capture);
    }

    @Test
    void shouldGetMember() {
        //given
        Long id = 1L;
        Subject subject = new Subject("Jan", "Brzechwa", 22);
        given(subjectRepository.findById(id)).willReturn(Optional.of(subject));
        //when
        Subject result = underTest.getSubject(id);

        //then
        verify(subjectRepository).findById(id);
        assertThat(result).isEqualTo(result);
    }

    @Test
    void shouldGetExceptionMember() {
        //given
        Long id = 1L;
        given(subjectRepository.findById(id)).willReturn(Optional.empty());
        //when

        //then
        assertThatThrownBy(()->underTest.getSubject(id))
                .hasMessage("Could not find cart with id: "+id);
    }

    @Test
    void ShouldDeleteMember() {
        //given
        Long id=1L;
        Subject member = new Subject("Jan", "Brzechwa", 22);
        when(subjectRepository.findById(id)).thenReturn(Optional.of(member));
        //when
        underTest.deleteSubject(id);
        //then
        ArgumentCaptor<Long> longArgumentCaptor=ArgumentCaptor.forClass(Long.class);
        verify(subjectRepository).deleteById(longArgumentCaptor.capture());
        Long idDeleted=longArgumentCaptor.getValue();

        assertThat(id).isEqualTo(idDeleted);
    }


}