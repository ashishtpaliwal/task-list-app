// Task List App JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Add animation to task completion
    const toggleButtons = document.querySelectorAll('.toggle-btn');
    toggleButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            const taskCard = this.closest('.task-card');
            taskCard.classList.add('completing');
            
            setTimeout(() => {
                taskCard.classList.remove('completing');
            }, 300);
        });
    });

    // Auto-hide alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.classList.add('fade');
            setTimeout(() => {
                alert.remove();
            }, 150);
        }, 5000);
    });

    // Search functionality with debounce
    const searchInput = document.querySelector('input[name="keyword"]');
    if (searchInput) {
        let searchTimeout;
        searchInput.addEventListener('input', function() {
            clearTimeout(searchTimeout);
            searchTimeout = setTimeout(() => {
                if (this.value.length >= 2 || this.value.length === 0) {
                    this.form.submit();
                }
            }, 500);
        });
    }

    // Filter change handlers
    const statusFilter = document.querySelector('select[name="status"]');
    const priorityFilter = document.querySelector('select[name="priority"]');
    
    if (statusFilter) {
        statusFilter.addEventListener('change', function() {
            this.form.submit();
        });
    }
    
    if (priorityFilter) {
        priorityFilter.addEventListener('change', function() {
            this.form.submit();
        });
    }

    // Form validation
    const forms = document.querySelectorAll('.needs-validation');
    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });

    // Character counter for text areas
    const textareas = document.querySelectorAll('textarea[maxlength]');
    textareas.forEach(textarea => {
        const maxLength = textarea.getAttribute('maxlength');
        const counter = document.createElement('small');
        counter.className = 'text-muted';
        counter.style.float = 'right';
        textarea.parentNode.appendChild(counter);
        
        function updateCounter() {
            const remaining = maxLength - textarea.value.length;
            counter.textContent = `${remaining} characters remaining`;
            
            if (remaining < 50) {
                counter.className = 'text-warning';
            } else if (remaining < 20) {
                counter.className = 'text-danger';
            } else {
                counter.className = 'text-muted';
            }
        }
        
        textarea.addEventListener('input', updateCounter);
        updateCounter();
    });

    // Keyboard shortcuts
    document.addEventListener('keydown', function(e) {
        // Ctrl/Cmd + N to open new task modal
        if ((e.ctrlKey || e.metaKey) && e.key === 'n') {
            e.preventDefault();
            const modal = document.getElementById('addTaskModal');
            if (modal) {
                const bootstrapModal = new bootstrap.Modal(modal);
                bootstrapModal.show();
                // Focus on title input
                setTimeout(() => {
                    const titleInput = modal.querySelector('#title');
                    if (titleInput) titleInput.focus();
                }, 300);
            }
        }
        
        // Escape to close modals
        if (e.key === 'Escape') {
            const modals = document.querySelectorAll('.modal.show');
            modals.forEach(modal => {
                const bootstrapModal = bootstrap.Modal.getInstance(modal);
                if (bootstrapModal) bootstrapModal.hide();
            });
        }
    });

    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });

    // Task card hover effects
    const taskCards = document.querySelectorAll('.task-card');
    taskCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-2px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });

    // Progress bar animation
    const progressBars = document.querySelectorAll('.progress-bar');
    progressBars.forEach(bar => {
        const width = bar.style.width;
        bar.style.width = '0%';
        setTimeout(() => {
            bar.style.transition = 'width 1s ease-in-out';
            bar.style.width = width;
        }, 100);
    });

    // Due date highlighting
    function highlightOverdueTasks() {
        const now = new Date();
        const taskCards = document.querySelectorAll('.task-card');
        
        taskCards.forEach(card => {
            const dueDateElement = card.querySelector('[data-due-date]');
            if (dueDateElement) {
                const dueDate = new Date(dueDateElement.getAttribute('data-due-date'));
                if (dueDate < now && !card.classList.contains('completed')) {
                    card.classList.add('border-danger');
                    card.style.borderWidth = '2px';
                }
            }
        });
    }

    // Call on page load
    highlightOverdueTasks();

    // Auto-save for edit forms (draft functionality)
    const editForms = document.querySelectorAll('form[action*="/edit"]');
    editForms.forEach(form => {
        const inputs = form.querySelectorAll('input, textarea, select');
        inputs.forEach(input => {
            // Load saved draft
            const draftKey = `draft_${form.action}_${input.name}`;
            const savedValue = localStorage.getItem(draftKey);
            if (savedValue && !input.value) {
                input.value = savedValue;
            }
            
            // Save draft on change
            input.addEventListener('input', function() {
                localStorage.setItem(draftKey, this.value);
            });
        });
        
        // Clear drafts on successful submit
        form.addEventListener('submit', function() {
            inputs.forEach(input => {
                const draftKey = `draft_${form.action}_${input.name}`;
                localStorage.removeItem(draftKey);
            });
        });
    });

    // Statistics animation
    const statValues = document.querySelectorAll('.stat-value');
    statValues.forEach(stat => {
        const finalValue = parseInt(stat.textContent);
        let currentValue = 0;
        const increment = Math.ceil(finalValue / 20);
        
        const counter = setInterval(() => {
            currentValue += increment;
            if (currentValue >= finalValue) {
                currentValue = finalValue;
                clearInterval(counter);
            }
            stat.textContent = currentValue;
        }, 50);
    });

    console.log('Task List App initialized successfully!');
});
